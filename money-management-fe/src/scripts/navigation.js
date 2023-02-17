export default {
    name: "NavigationComponents",
    methods: {
        logOut() {
            this.$store.dispatch('authenticate/logout');
            this.$router.push({name: 'home'});
        }
    },
    computed: {
        currentUser() {
            return this.$store.state.authenticate.user;
        },
    }
}