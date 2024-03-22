<template>
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6 col-lg-4">
          <h2 class="text-center mb-4">Admin Login</h2>
          <form @submit.prevent="login">
            <div class="mb-3">
              <label for="username" class="form-label">Username:</label>
              <input
                type="text"
                id="username"
                v-model="username"
                class="form-control"
                
              />
              <div v-if="!username" class="text-danger mt-1">Please enter your username.</div>
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">Password:</label>
              <input
                type="password"
                id="password"
                v-model="password"
                class="form-control"
                
              />
              <div v-if="!password" class="text-danger mt-1">Please enter your password.</div>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
            <div v-if="loginSuccess" class="text-success mt-3">Login successful!</div>
            <div v-if="loginError" class="text-danger mt-3">{{ loginError }}</div>
          </form>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  export default {
    name: "adminLogin",
    data() {
      return {
        username: "",
        password: "",
        loginSuccess: false,
        loginError: "",
      };
    },
    methods: {
      login() {
        // Clear previous messages
        this.loginSuccess = false;
        this.loginError = "";
  
        // Check if inputs are empty
        if (!this.username || !this.password) {
          return;
        }
  
        axios
          .post("/api/admin/login", {
            username: this.username,
            password: this.password,
          })
          .then(() => {
            // Handle successful login
            console.log("Login successful");
            this.loginSuccess = true;
            window.location.href = "/admin";
            // Redirect or perform other actions
          })
          .catch((error) => {
            // Handle login failure
            console.error("Login failed", error);
            this.loginError = "Incorrect username or password";
          });
      },
    },
  };
  </script>
  
  <style>
  /* Your custom styles here */
  </style>
  