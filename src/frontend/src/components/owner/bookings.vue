<template>
    <div v-if="loggedIn">
      <div class="container-fluid">
        <div class="row flex-nowrap">
          <!-- Sidebar -->
          <div class="bg-dark col-auto col-md-4 col-lg-3 min-vh-100 d-flex flex-column justify-content-between">
            <div class="bg-dark p-2">
              <a href="" class="d-flex text-decoration-none mt-1 align-items-center text-white">
                <span class="fs-4 d-none d-sm-inline">Owners Dashboard</span>
              </a>
              <ul class="nav nav-pills flex-column mt-4">
                <li class="nav-item py-2 py-sm-0">
                  <a href="/owner/dashboard" class="nav-link text-white">
                    <i class='bx bxs-dashboard'></i>
                    <span class="fs-6 d-none ms-3 d-sm-inline">Dashboard</span>
                  </a>
                </li>
                <li class="nav-item py-2 py-sm-0">
                  <a href="/owner/profile" class="nav-link text-white">
                    <i class='bx bxs-user-circle'></i>
                    <span class="fs-6 d-none ms-3 d-sm-inline">Profile</span>
                  </a>
                </li>
                <li class="nav-item py-2 py-sm-0">
                  <a href="/owner/vehicle" class="nav-link text-white">
                    <i class='bx bx-list-ul'></i>
                    <span class="fs-6 d-none ms-3 d-sm-inline">Vehicles</span>
                  </a>
                </li>
                <li class="nav-item py-2 py-sm-0">
                  <a href="/owner/bookings" class="nav-link active">
                    <i class='bx bx-book-content'></i>
                    <span class="fs-6 d-none ms-3 d-sm-inline">Bookings</span>
                  </a>
                </li>
              </ul>
            </div>
            <!-- Dropdown -->
            <div class="dropdown open p-3">
              <button class="btn border-none dropdown-toggle text-white" type="button" id="triggerId" data-bs-toggle="dropdown" aria-expanded="false">
                <i class='bx bxs-user-circle'></i>
                <span class="ms-2">{{ owner.fullname }}</span>
              </button>
              <div class="dropdown-menu" aria-labelledby="triggerId">
                <button class="dropdown-item" @click="logout">Logout</button>
              </div>
            </div>
          </div>
          <!-- Content -->
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
                  <th>Booked By</th>
                  <th>Total Fare</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(booking, index) in ownerBookings" :key="booking.booking_id">
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
                  <td>{{ booking.user.name }}</td>
                  <td>{{ booking.total_fare }}</td>
                  <td>
                    <button class="btn btn-danger" @click="cancelBooking(booking.booking_id)">
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
  
  <style>
  .nav-pills li a:hover {
    background-color: blue;
  }
  </style>
  
  <script>
  import axios from "axios";
  export default {
    name: "ownerBookings",
    data() {
      return {
        owner: {},
        loggedIn: false,
        bookings: [],
      };
    },
    computed: {
      ownerBookings() {
        // Filter bookings for vehicles owned by the logged-in owner
        return this.bookings.filter(
          (booking) => booking.vehicles.owner_id === this.owner.owner_id
        );
      },
    },
    created() {
      this.fetchOwnerData();
      this.fetchBookings();
    },
    methods: {
      fetchOwnerData() {
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
      fetchBookings() {
        axios
          .get("/api/bookings/all")
          .then((response) => {
            this.bookings = response.data;
          })
          .catch((error) => {
            console.error("Error fetching bookings:", error);
          });
      },
      cancelBooking(bookingId) {
        axios.post(`/api/bookings/cancel/${bookingId}`).then(() => {
          this.bookings = this.bookings.filter(
            (booking) => booking.booking_id !== bookingId
          );
        });
      },
      getSerialNumber(index) {
        return index + 1;
      },
      logout() {
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
  