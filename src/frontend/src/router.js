import { createWebHistory, createRouter } from 'vue-router'
import HomePage from './components/HomeComponent.vue';
import OwnerSignIn from './components/owner/owner-signin.vue';
import ownerRegister from './components/owner/register.vue';
import userLogin from './components/user/login.vue';
import userRegister from './components/user/register.vue';
import userDashboard from './components/user/dashboard.vue';
import ownerDashboard from './components/owner/dashboard.vue';
import vehicleDashboard from './components/owner/vehicles.vue';
import addVehicle from './components/owner/add-vehicle.vue';
import searchResult from './components/user/search.vue';
import quotePage from './components/user/quote.vue';
import userProfile from './components/user/profile.vue';
import userBooking from './components/user/bookings.vue';
import ownerProfile from './components/owner/profile.vue';
import editVehicle from './components/owner/edit-vehicle.vue';
import adminLogin from './components/admin/login.vue';
import adminDashboard from './components/admin/dashboard.vue';
import ownerManagement from './components/admin/owner-management.vue';
import featureRequests from './components/admin/feature-request.vue';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomePage,
    },
    {
        path: '/owner/login',
        name: 'OwnerSignin',
        component: OwnerSignIn
    },
    {
        path: '/ownersignup',
        name: 'OwnerSignUp',
        component: ownerRegister
    },
    {
        path: '/user/login',
        name: 'userLogin',
        component: userLogin
    },
    {
        path: '/user/signup',
        name: 'userRegister',
        component: userRegister
    },
    {
        path: '/user/dashboard',
        name: 'userDashboard',
        component: userDashboard,
        props: true,
    },
    {
        path: '/user/profile',
        name: 'userProfile',
        component: userProfile,
    },
    {
        path: '/user/booking',
        name: 'userBooking',
        component: userBooking,
    },
    {
        path: '/owner/dashboard',
        name: 'ownerDashboard',
        component: ownerDashboard
    },
    {
        path: '/owner/profile',
        name: 'ownerProfile',
        component: ownerProfile
    },
    {
        path: '/owner/vehicle',
        name: 'vehicleDashboard',
        component: vehicleDashboard
    },
    {
        path: '/owner/add-vehicle',
        name: 'addVehicle',
        component: addVehicle
    },
    {
        path: '/owner/edit-vehicle/:vehicleId',
        name: 'editVehicle',
        component: editVehicle
    },
    {
        path: '/search',
        name: 'searchResult',
        component: searchResult
    },
    {
        path: '/quote',
        name: 'quotePage',
        component: quotePage
    },
    
    {
        path: '/admin/login',
        name: 'adminLogin',
        component: adminLogin
    },
    {
        path: '/admin',
        name: 'adminDashboard',
        component: adminDashboard
    },
    {
        path: '/admin/owner',
        name: 'ownerManagement',
        component: ownerManagement
    },
    {
        path: '/admin/feature-requests',
        name: 'featureRequests',
        component: featureRequests
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;
