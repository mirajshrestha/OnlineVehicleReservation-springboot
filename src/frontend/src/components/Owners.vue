<template>
  <div class="container">
    <h1 class="text-center">Employees List</h1>

    <table class="table table-striped">
      <thead>
        <tr>
          <th>Id</th>
          <th>Full Name</th>
          <th>Email</th>
          <th>Contact</th>
          <th>Password</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="owner in owners" :key="owner.id">
          <td>{{ owner.owners_id }}</td>
          <td>{{ owner.fullname }}</td>
          <td>{{ owner.email }}</td>
          <td>{{ owner.contact }}</td>
          <td>{{ owner.password }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "OnwerList",
  data() {
    return {
      owners: [],
    };
  },
  mounted() {
    fetch("/api/owner/owners")
      .then((response) => response.json())
      .then((data) => {
        this.owners = data;
        console.log(data);
      });
  },
};
</script>