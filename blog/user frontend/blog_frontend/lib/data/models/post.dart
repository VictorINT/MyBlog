import 'dart:convert';

class Post {
  final int id;
  final String title;
  final String content;

  Post({required this.id, required this.title, required this.content});

  // Factory constructor to create a Post from JSON
  factory Post.fromJson(Map<String, dynamic> json) {
    return Post(
      id: json['id'],
      title: json['title'],
      content: json['content'],
    );
  }

  // Convert list of JSON objects into list of Posts
  static List<Post> fromJsonList(String jsonString) {
    final data = json.decode(jsonString) as List;
    return data.map((json) => Post.fromJson(json)).toList();
  }
}
