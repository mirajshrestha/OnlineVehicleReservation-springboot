<template>
  <navbar />
  <section></section>

  <section class="services" id="services">
    <div class="heading">
      <span>Search Results</span>
      <div class="services-container">
        <div v-if="searchResults.length > 0">
          <div
            class="wrapper"
            v-for="vehicle in searchResults"
            :key="vehicle.id"
          >
            <div class="view_main">
              <div class="view_wrap list-view" style="display: block">
                <div class="view_item">
                  <div class="vi_left">
                    <img
                      :src="`http://localhost:3000/api/images/${vehicle.carimg}`"
                      alt="photo"
                      style="width: 250px; height: 250px;"
                    />
                  </div>
                  <div class="vi_right">
                    <p class="Manufacturer">{{ vehicle.manufacturer }}</p>
                    <p class="Model">{{ vehicle.model }}</p>
                    <p class="DailyFare">Rs. {{ vehicle.dailyfare }}/day</p>
                    <p class="FromTo">
                      {{ vehicle.fromdate }} / {{ vehicle.todate }}
                    </p>
                    <!-- <p class="FromTo">{{ vehicle.vehicle_id }}</p> -->

                    <!-- <a href="" class="btn btn-secondary">View Details</a> -->
                    <!-- <a href="/quote?vehicleId=${vehicle.id}" class="btn btn-primary">Book this car</a> -->
                    <!-- <router-link :to="`/quote?vehicleId=${vehicle.vehicle_id}`" class="btn btn-primary">Book this car</router-link> -->
                    <router-link
                      :to="{
                        path: '/quote',
                        query: {
                          vehicleId: vehicle.vehicle_id,
                          pickUpDate: pickUpDate,
                          returnDate: returnDate,
                        },
                      }"
                      class="btn btn-primary"
                      >Book this car</router-link
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style>
.wrapper {
  width: 800px;
  margin: 20px auto;
}
.view_main {
  background: #fff;
  border-radius: 3px;
  padding: 15px;
}
.list-view .view_item {
  background: #fff;
  border: 2px solid #e2efe1;
  margin: 20px;
  padding: 20px 20px;
  display: flex;
  align-items: center;
}

.list-view .view_item:last-child {
  margin-bottom: 0;
}

.list-view .view_item .vi_left {
  margin-right: 25px;
}
.view_item .Manufacturer {
  font-weight: 600;
  font-size: 20px;
}

.view_item .Model {
  font-weight: 500;
  font-size: 16px;
}

.view_item .DailyFare {
  font-weight: 400;
  font-size: 14px;
}

.view_item .FromTo {
  font-weight: 300;
  font-size: 12px;
  margin-bottom: 15px;
}
.view_item a {
  /* width: 80px; */
  /* background: #4abd3e; */
  padding: 10px 50px;
  margin: 20px;
  border-radius: 3px;
  color: #fff;
  text-align: center;
  font-weight: 200;
  cursor: pointer;
}
.view_item .btn:hover {
  background: #3bd62b;
}
.grid-view {
  width: 100%;
}

.grid-view .view_item {
  display: inline-block;
  border: 1px solid #e2efe1;
  width: 230px;
  padding: 25px;
  text-align: center;
  margin: 10px;
}

.grid-view .view_item .vi_left {
  margin-bottom: 10px;
}

.grid-view .view_item .btn {
  margin: 0 auto;
}
</style>

<script>
import axios from "axios";
import Navbar from "../Navbar.vue";
export default {
  components: { Navbar },
  name: "searchResult",
  data() {
    return {
      searchResults: [],
      pickUpDate: "",
      returnDate: "",
    };
  },
  created() {
    this.pickUpDate = this.$route.query.pickUpDate || "";
    this.returnDate = this.$route.query.returnDate || "";
    this.fetchSearchResults();
  },
  methods: {
    fetchSearchResults() {
      const location = this.$route.query.location;
      const pickUpDate = this.$route.query.pickUpDate;
      const returnDate = this.$route.query.returnDate;
      axios
        .get("/api/vehicle/search", {
          params: {
            location,
            pickUpDate,
            returnDate,
          },
        })
        .then((response) => {
          this.searchResults = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>