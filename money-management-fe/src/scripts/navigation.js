export default {
    name: "NavigationComponents",
    methods: {
        logOut() {
            this.$store.dispatch('authenticate/logout');
            this.$router.push({name: 'Home'});
        }
    },
    computed: {
        currentUser() {
            return this.$store.state.authenticate.user;
        },
    }
}