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
                <a href="/user/dashboard" class="nav-link text-white"
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
                <a href="/user/booking" class="nav-link active"
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
          <h2>Bookings</h2>
          <hr />
          <table class="table table-striped" style="width: 75%">
            <thead>
              <tr>
                <th>SN</th>
                <th>Image</th>
                <th>Manufacturer</th>
                <th>Model</th>
                <th>Location</th>

                <th>From</th>
                <th>To</th>
                <th>Vehicle Owner</th>
                <!-- <th>Owner email</th> -->

                <th>Total Fare</th>

                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(booking, index) in userBookings"
                :key="booking.booking_id"
              >
                <td>{{ getSerialNumber(index) }}</td>
                <td>
                  <img
                    :src="`http://localhost:3000/api/images/${booking.vehicles.carimg}`"
                    alt="Vehicle Image"
                    style="width: 100px; height: 100px"
                  />
                </td>
                <td>{{ booking.vehicles.manufacturer }}</td>
                <td>{{ booking.vehicles.model }}</td>
                <td>{{ booking.vehicles.availablelocation }}</td>

                <td>{{ booking.fromdate }}</td>
                <td>{{ booking.todate }}</td>
                <td>{{ booking.owners.fullname }}</td>
                <!-- <td>{{ booking.owners.email }}</td> -->

                <td>{{ booking.total_fare }}</td>
                <td>
                  <!-- Add any actions or buttons related to each booking here -->
                  <button
                    class="btn btn-danger"
                    @click="cancelBooking(booking.booking_id)"
                  >
                    <i class="bx bx-x"></i> Cancel
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
import axios from "axios";
export default {
  name: "userProfile",
  data() {
    return {
      user: {},
      loggedIn: false,
      bookings: [],
    };
  },
  computed: {
    userBookings() {
      // Filter bookings based on user_id
      return this.bookings.filter(
        (booking) => booking.user.user_id === this.user.user_id
      );
    },
  },
  created() {
    this.fetchUserData();
    this.fetchUserBookings();
  },
  mounted() {
    console.log("user:", this.user.user_id);
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
    cancelBooking(bookingId) {
      axios.post(`/api/bookings/cancel/${bookingId}`).then(() => {
        console.log(`Booking ${bookingId} canceled successfully`);
        this.bookings = this.bookings.filter(
          (booking) => booking.booking_id !== bookingId
        );
      });
      console.log(`Cancel booking with ID: ${bookingId}`);
    },
    fetchUserBookings() {
      axios
        .get(`/api/bookings/all`)
        .then((response) => {
          this.bookings = response.data;
          console.log("bookings", this.bookings);

          this.bookings.forEach((booking) => {
            console.log("Booking ID:", booking.booking_id);
            console.log("Vehicle Image:", booking.vehicles.carimg);
            console.log("Manufacturer:", booking.vehicles.manufacturer);
            console.log("Model:", booking.vehicles.model);
            console.log("From Date:", booking.fromdate);
            console.log(
              "Available Location:",
              booking.vehicles.availablelocation
            );
            console.log("user:", booking.user.user_id);
            console.log("To Date:", booking.todate);
            console.log("Total Fare:", booking.total_fare);
            console.log("---");
          });
        })
        .catch((error) => {
          console.error("Error fetching user bookings:", error);
        });
    },
    getSerialNumber(index) {
      return index + 1;
    },
    updateProfile() {
      axios
        .put("/api/user/update", {
          name: this.user.name,
          address: this.user.address,
          contact: this.user.contact,
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