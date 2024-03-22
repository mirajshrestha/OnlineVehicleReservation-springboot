<template>
  <header>
    <a href="/" class="logo"><img src="../../public/assets/images/jeep.png" /></a>
    <div class="bx bx-menu" id="menu-icon"></div>

    <ul class="navbar">
      <li><a href="/#home">Home</a></li>
      <li><a href="/#ride">Ride</a></li>
      <li><a href="/#services">Services</a></li>
      <li><a href="/#about">About</a></li>
      <!-- <li><a href="/#reviews">Reviews</a></li> -->
    </ul>
    <div class="header-btn" v-if="userLog">
      <!-- <a href="/ownersignup" class="sign-up">Sign Up</a> -->
      
      <a href="/user/dashboard" class="sign-in"><i class='bx bxs-user-circle' ></i> {{ user.name }}</a>
    </div>
    <div class="header-btn" v-else-if="ownerLog">
      <!-- <a href="/ownersignup" class="sign-up">Sign Up</a> -->
      <a href="/owner/dashboard" class="sign-in"><i class='bx bxs-user-circle' ></i> {{ owner.fullname }}</a>
    </div>
    <div class="header-btn" v-else>
      <a href="/ownersignup" class="sign-up">Sign Up</a>
      <a href="/owner/login" class="sign-in">Sign In</a>
    </div>
  </header>
</template>


<script>
import axios from 'axios';
export default {
  name: "NavBar",
  data() {
    return{
      user: {},
      owner: {},
      userLog: false,
      ownerLog: false
    };
  },
  created(){
    this.fetchUserData();
    this.fetchOwnerData();
  },
  methods: {
    fetchUserData(){
      axios.get('/api/user/me')
      .then(response => {
        this.user = response.data;
        this.userLog = true;
      }).catch(error => {
        console.error(error);
      });
    },
    fetchOwnerData(){
      axios.get('/api/owner/me')
      .then(response => {
        this.owner = response.data;
        this.ownerLog = true;
      }).catch(error => {
        console.error(error);
      });
    }
  }
};
</script>
