<template>
  <!-- <div v-if="loggedIn">
      <h2>Welcome, {{ owner.email }}</h2>
      <p>Email: {{ owner.fullname }} </p>
      <button @click="logout">Logout</button>
    </div> -->
  <div v-if="loggedIn">
    <div class="container-fluid">
      <div class="row flex-nowrap">
        <div
          class="bg-dark col-auto col-md-4 col-lg-3 min-vh-100 d-flex flex-column justify-content-between"
        >
          <div class="bg-dark p-2">
            <a
              href="/"
              class="d-flex text-decoration-none mt-1 align-items-center text-white"
            >
              <span class="fs-4 d-none d-sm-inline"
                >Online Vehicle Reservation</span
              >
            </a>
            <ul class="nav nav-pills flex-column mt-4">
              <li class="nav-item py-2 py-sm-0">
                <a href="/owner/dashboard" class="nav-link text-white"
                  ><i class="bx bxs-dashboard"></i
                  ><span class="fs-6 d-none ms-3 d-sm-inline"
                    >Dashboard</span
                  ></a
                >
              </li>
              <li class="nav-item py-2 py-sm-0">
                <a href="/owner/profile" class="nav-link active"
                  ><i class="bx bxs-user-circle"></i
                  ><span class="fs-6 d-none ms-3 d-sm-inline">Profile</span></a
                >
              </li>
              <li class="nav-item py-2 py-sm-0">
                <a href="/owner/vehicle" class="nav-link text-white"
                  ><i class="bx bx-list-ul"></i
                  ><span class="fs-6 d-none ms-3 d-sm-inline">Vehicles</span></a
                >
              </li>
              <li class="nav-item py-2 py-sm-0">
                <a href="#" class="nav-link text-white"
                  ><i class="bx bx-book-content"></i
                  ><span class="fs-6 d-none ms-3 d-sm-inline">Bookings</span></a
                >
              </li>
            </ul>
          </div>
          <div class="dropdown open p-3">
            <button
              class="btn border-none dropdown-toggle text-white"
              type="button"
              id="triggerId"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="bx bxs-user-circle"></i
              ><span class="ms-2">{{ owner.fullname }}</span>
            </button>
            <div class="dropdown-menu" aria-labelledby="triggerId">
              
              <button class="dropdown-item" href="#" @click="logout">
                Logout
              </button>
            </div>
          </div>
        </div>
        <!-- content  -->
        <div class="p-3">
          <h2>Profile</h2>
          <hr />
          <form @submit.prevent="updateProfile" class="w-75">
            <div class="mb-3">
              <label for="name" class="form-label">Name:</label>
              <input
                type="text"
                id="name"
                name="name"
                v-model="owner.fullname"
                placeholder="Name"
                class="form-control"
              />
            </div>
            <div class="mb-3">
              <label for="email" class="form-label">Email:</label>
              <input
                type="email"
                id="email"
                name="email"
                v-model="owner.email"
                placeholder="Email"
                class="form-control"
                disabled
              />
            </div>
            
            <div class="mb-3">
              <label for="contact" class="form-label">Contact:</label>
              <input
                type="text"
                id="contact"
                name="contact"
                v-model="owner.contact"
                placeholder="Contact"
                class="form-control"
              />
            </div>
            
            <button type="submit" class="btn btn-primary">
              Update Profile
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <style>
.nav-pills li a:hover {
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
    updateProfile() {
      axios
        .put("/api/owner/update", {
          fullname: this.owner.fullname,
          
          contact: this.owner.contact,
        })
        .then(() => {
          console.log("Profile updated successfully");
          alert("Profile updated successfully");
        })
        .catch((error) => {
          console.error("Error updating profile:", error);
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
    },
  },
};
</script>