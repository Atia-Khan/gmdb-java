package com.gmdb.gmdb;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.EvalTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.gmdb.gmdb.models.Movies;
import com.gmdb.gmdb.models.Reviews;
import com.gmdb.gmdb.models.Users;
import com.gmdb.gmdb.repositories.IMoviesRepository;
import com.gmdb.gmdb.repositories.IReviewsRepository;
import com.gmdb.gmdb.repositories.IUserRepository;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.context.web.WebAppConfiguration;

@AutoConfigureMockMvc
@WebAppConfiguration
@AutoConfigureJsonTesters
@SpringBootTest
public class GmdbApplicationTests {

    // @Autowired
    private MockMvc mvc;
	// Stories for this project are shown below in order of value, with the highest value listed first.
    // This microservice will contain the CRUD operations required to interact with the GMDB movie database.
    // Other functionality (e.g. user authentication) is hosted in other microservices.
    //
    @Autowired
    private WebApplicationContext context;

    @MockBean
    IMoviesRepository repo;

    @MockBean
    IUserRepository repoUser;

    @MockBean
    IReviewsRepository repoReview;

    @Autowired
    private JacksonTester<List<Movies>> moviesJacsonList;

    @Autowired
    private JacksonTester<Movies> moviesJacson;

    @Autowired
    private JacksonTester<Users> userJackson;

    @Autowired
    private JacksonTester<Reviews> reviewJackson;
    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        
    }


    // 1. As a user
    //    I can GET a list of movies from GMDB that includes Movie ID | Movie Title | Year Released | Genre | Runtime
    //    so that I can see the list of available movies.
    //
    // @Mock IMoviesRepository repo;

    @Test
    public void getMovies() throws Exception{
        List<Movies> movies = new ArrayList<>();

        movies.add(new Movies(1,"dfssdf",2000,"dsfsdf","sdfdf"));
        movies.add(new Movies(2,"dfssdf",2000,"dsfsdf","sdfdf"));

        
        when(repo.findAll()).thenReturn(movies);

        mvc.perform(get("/movies/showlist")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(moviesJacsonList.write(movies).getJson()));
    }
    // 2. As a user
    //    I can provide a movie ID and get back the record shown in story 1, plus a list of reviews that contains Review ID | Movie ID | Reviewer ID | Review Text | DateTime last modified
    //    so that I can read the reviews for a movie.
    //

    @Test
    public void getMovieById() throws Exception{
        Movies movies = new Movies(1,"Fast and Furios",2000,"first","a");
        when(repo.findById(1)).thenReturn(movies);

        mvc.perform(get("/movies/get/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(moviesJacson.write(movies).getJson()));
    }
    // 3. As a user
    //    I can provide a Reviewer ID and get back a record that contains Reivewer ID | Username | Date Joined | Number of Reviews
    //    so that I can see details about a particular reviewer.
    //

    @Test
    public void getReviwerById() throws Exception{
        Users reviewer = new Users(1,"test reviewer","2023-05-19");
        when(repoUser.findById(1)).thenReturn(reviewer);

        mvc.perform(get("/users/get/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(userJackson.write(reviewer).getJson()));
    }
    // 4. As a user
    //    I can register as a reviewer by providing my Username. (Reviewer ID should be autogenerated)
    //    So that I can start reviewing movies.
    //
    @Test
    public void addUser() throws Exception{
        Users user = new Users(1,"test reviewer","2023-05-19");
        when(repoUser.save(user)).thenReturn(user);

        mvc.perform(post("/users/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(userJackson.write(user).getJson()))
        .andExpect(status().isOk())
        .andExpect(content().string("added"));
    }
    // 5. As a reviewer
    //    I can post a review by providing my reviewer ID, a movie ID and my review text. (Review ID should be autogenerated)
    //    So that I can share my opinions with others.
    //
    @Test
    public void addReview() throws IOException, Exception{
        Users user = new Users(1,"test reviewer","2023-05-19");
        Movies movie = new Movies(1,"dfssdf",2000,"dsfsdf","sdfdf");
        Reviews review = new Reviews(1, movie, user, "Best movie");

        when(repoReview.save(review)).thenReturn(review);

        mvc.perform(post("/reviews/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(reviewJackson.write(review).getJson()))
        .andExpect(status().isOk())
        .andExpect(content().string("Review Added"));
    }
    // 6. As a reviewer
    //    I can delete a review by providing my reviewer ID and a review ID
    //    So that I can remove reviews I no longer wish to share.
    //

    @Test
    public void deleteReview() throws Exception{

        mvc.perform(delete("/reviews/delete/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Deleted"));
    }
    // 7. As a reviewer
    //    I can update a review by providing my reviewer ID, a movie ID and my review text.
    //    So that I can modify the opinion I'm sharing with others.
    //

    @Test
    public void updateReview()throws Exception{
        Users user = new Users(1,"test reviewer","2023-05-19");
        Movies movie = new Movies(1,"dfssdf",2000,"dsfsdf","sdfdf");
        Reviews review = new Reviews(1, movie, user, "Best movie");

        when(repoReview.save(review)).thenReturn(review);

        Reviews updatedReview = new Reviews(1, movie, user, "Good movie");

        mvc.perform(post("/reviews/update")
        .contentType(MediaType.APPLICATION_JSON)
        .content(reviewJackson.write(updatedReview).getJson()))
        .andExpect(status().isOk())
        .andExpect(content().string("Review updated"));
    }
    // 8. As an Admin
    //    I can add a new movie to the database by providing the data listed in story 1 (Movie ID should be autogenerated)
    //    so that I can share new movies with the users.
    @Test
    public void addMovie() throws Exception{
        Movies movie = new Movies(1,"dfssdf",2000,"dsfsdf","sdfdf");

        when(repo.save(movie)).thenReturn(movie);

        mvc.perform(post("/movies/addMovies")
        .content(moviesJacson.write(movie).getJson())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Movie Saved as "+ movie.getTitle()));
    }
    // 9. As an Admin
    //    I can add update the entry for a movie by providing the data listed in Story 1.
    //    so that I can correct errors in previously uploaded movie entries.
    //
    //10. As an admin
    //    I can delete a movie by providing a movie ID
    //    so that I can remove movies I no longer wish to share.
    //
    //11. As an admin
    //    I can impersonate a reviewer and do any of the things they can do
    //    so that I can help confused reviewers.

	// @Test
	// public void contextLoads() {
	// }

}
