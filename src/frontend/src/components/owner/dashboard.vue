<template>
  <!-- <div v-if="loggedIn">
    <h2>Welcome, {{ owner.email }}</h2>
    <p>Email: {{ owner.fullname }} </p>
    <button @click="logout">Logout</button>
  </div> -->
  <div v-if="loggedIn">
  <div class="container-fluid">
    <div class="row flex-nowrap">
      <div class="bg-dark col-auto col-md-4 col-lg-3 min-vh-100 d-flex flex-column justify-content-between">
        <div class="bg-dark p-2">
          <a href="" class="d-flex text-decoration-none mt-1 align-items-center text-white">
            <span class="fs-4 d-none d-sm-inline">Owners Dashboard</span>
          </a>
          <ul class="nav nav-pills flex-column mt-4">
            <li class="nav-item py-2 py-sm-0">
                <a href="/owner/dashboard" class="nav-link active"><i class='bx bxs-dashboard' ></i><span class="fs-6 d-none ms-3 d-sm-inline">Dashboard</span></a>
            </li>
            <li class="nav-item py-2 py-sm-0">
                <a href="/owner/profile" class="nav-link text-white"><i class='bx bxs-user-circle' ></i><span class="fs-6 d-none ms-3 d-sm-inline">Profile</span></a>
            </li>
            <li class="nav-item py-2 py-sm-0">
                <a href="/owner/vehicle" class="nav-link text-white"><i class='bx bx-list-ul'></i><span class="fs-6 d-none ms-3 d-sm-inline">Vehicles</span></a>
            </li>
            <li class="nav-item py-2 py-sm-0">
                <a href="#" class="nav-link text-white"><i class='bx bx-book-content'></i><span class="fs-6 d-none ms-3 d-sm-inline">Bookings</span></a>
            </li>
          </ul>
        </div>
        <div class="dropdown open p-3">
            <button class="btn border-none dropdown-toggle text-white" type="button" id="triggerId" data-bs-toggle="dropdown" aria-expanded="false">
                <i class='bx bxs-user-circle' ></i><span class="ms-2">{{ owner.fullname }}</span>
            </button>
            <div class="dropdown-menu" aria-labelledby="triggerId">
                <button class="dropdown-item" href="#" onclick="openSection('Profile')">Profile</button>
                <button class="dropdown-item" href="#" @click="logout">Logout</button>
            </div>
        </div>  
        
        
      </div>
      <!-- content  -->
        <div class="p-3" >
            <h2>Dashboard</h2>
            <hr>
            <p>Hello, {{ owner.fullname }}.</p>
            <p>This is your dashboard. Here you can edit your profile as you wish.</p>
        </div>
        
        
    </div>
  </div>
  
</div>

</template>

<style>
.nav-pills li a:hover{
    background-color: blue;
}
</style>



<script>
import axios from "axios";
export default {
  name: "ownerDashboard",
  data() {
    return {
      owner: {},
      loggedIn: false,
    };
  },
  created() {
    this.fetchUserData();
  },
  methods: {
    fetchUserData() {
      axios
        .get("/api/owner/me")
        .then((response) => {
          this.owner = response.data;
          this.loggedIn = true;
        })
        .catch((error) => {
          console.error(error);
          this.$router.push("/owner/login");
        });
    },
    logout() {
      console.log("logout working");
      axios
        .get("/api/owner/logout")
        .then(() => {
          window.location.href = "/owner/login";
        })
        .catch((error) => {
          console.error(error);
        });
    }
  },
};
</script>