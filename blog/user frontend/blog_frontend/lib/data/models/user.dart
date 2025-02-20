import 'dart:convert';
import 'dart:ffi';

class User {
  final String sub;
  final String name;
  final String given_name;
  final String family_name;
  final Uri picture;
  final String email;
  final Bool email_verified;

  User({required this.sub, required this.name, required this.given_name, required this.family_name, required this.picture, required this.email, required this.email_verified});

  // Factory constructor to create a Post from JSON
  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      sub: json['sub'],
      name: json['name'],
      given_name: json['given_name'],
      family_name: json['family_name'],
      picture: json['picture'],
      email: json['email'],
      email_verified: json['email_verified'],
    );
  }
}
