<template>
  <!-- <div v-if="loggedIn">
      <h2>Welcome, {{ user.name }}</h2>
      <p>Email:  {{ user.email }}</p>
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
                <a href="/user/dashboard" class="nav-link active"
                  ><i class="bx bxs-dashboard"></i
                  ><span class="fs-6 d-none ms-3 d-sm-inline"
                    >Dashboard</span
                  ></a
                >
              </li>
              <li class="nav-item py-2 py-sm-0">
                <a href="/user/profile" class="nav-link text-white"
                  ><i class="bx bxs-user-circle"></i
                  ><span class="fs-6 d-none ms-3 d-sm-inline">Profile</span></a
                >
              </li>

              <li class="nav-item py-2 py-sm-0">
                <a href="/user/booking" class="nav-link text-white"
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
              ><span class="ms-2">{{ user.name }}</span>
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
          <h2>Dashboard</h2>
          <hr />
          <p>Hello, {{ user.name }}.</p>
          <p>
            This is your dashboard. Here you can edit your profile as you wish.
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "userDashboard",
  data() {
    return {
      user: {},
      loggedIn: false,
    };
  },
  created() {
    this.fetchUserData();
  },
  methods: {
    fetchUserData() {
      axios
        .get("/api/user/me")
        .then((response) => {
          this.user = response.data;
          this.loggedIn = true;
        })
        .catch((error) => {
          console.error(error);
          this.$router.push("/user/login");
        });
    },
    logout() {
      console.log("logout working");
      axios
        .get("/api/user/logout")
        .then(() => {
          window.location.href = "/user/login";
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>