package pkg;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import pkg.data.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pkg.model.Post;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlogController {
    @Autowired
    private PostRepository postRepository;

    public BlogController(PostRepository p) {
        this.postRepository = p;
    }
    @GetMapping("/")
    public String listPosts(ModelMap m){
    //public List<Post> listPosts(){
        List<Post> allPosts = this.postRepository.getAllPosts();
        m.put("posts", allPosts);
        return "home";
        //return allPosts;
    }

    @GetMapping("/post/{id}")
    public String postDetails(@PathVariable Long id, ModelMap modelMap){
        Post post = postRepository.findById(id);
        modelMap.put("post", post);
        return  "post-details";
    }
}
