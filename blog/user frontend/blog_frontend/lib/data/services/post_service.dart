import 'package:blog_frontend/data/models/post.dart';
import 'package:blog_frontend/data/models/user.dart';
import 'package:http/http.dart' as http;

class PostService {
  static const String apiUrl = "http://localhost:8080/posts"; // API URL

  // Function to fetch posts
  static Future<List<Post>> fetchPosts() async {
    try {
      final response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        return Post.fromJsonList(response.body);
      } else {
        throw Exception("Failed to load posts");
      }
    } catch (e) {
      throw Exception("Error: $e");
    }
  }
  // Function to fetch first N posts
  static Future<List<Post>> fetchFirstNPosts(int n) async {
    try {
      final response = await http.get(Uri.parse("$apiUrl/firstN?n=$n"));

      if (response.statusCode == 200) {
        return Post.fromJsonList(response.body);
      } else {
        throw Exception("Failed to load posts");
      }
    } catch (e) {
      throw Exception("Error: $e");
    }
  }
  // Function to fetch posts by name
  static Future<List<Post>> fetchPostsByName(String name) async {
    try{
      final response = await http.get(Uri.parse("$apiUrl/searchName?name=$name"));
      if(response.statusCode == 200){
        return Post.fromJsonList(response.body);
      } else {
        throw Exception("Failed to load posts");
      }
    } catch(e){
      throw Exception("Error: $e");
    }
  }
  // user/me information
  static Future<User> fetchUser() async {
    try {
      final response = await http.get(Uri.parse("$apiUrl/user/me"));

      if (response.statusCode == 200) {
        return User.fromJson(response.body as Map<String, dynamic>);
      } else {
        throw Exception("Failed to load user");
      }
    } catch (e) {
      throw Exception("Error: $e");
    }
  }
  /**
   * TODO:
   * 1) remake login page
   * 2) add POST DELETE PUT
   * 3) make pages/widgets for all of them
   * 4) implement RiverPod
   * 5) make it pretty
   */
}
