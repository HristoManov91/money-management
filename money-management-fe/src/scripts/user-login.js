import {email, maxLength, minLength, required} from "vuelidate/lib/validators";
import MessageModal from "@/components/MessageModal.vue";

export default {
    name: "UserLoginComponent",
    components: {
        MessageModal,
    },
    data() {
        return {
            user: {
                email: null,
                password: null
            },
            message: null,
            messageClass: null
        }
    },
    validations: {
        user: {
            email: {
                required,
                email,
                minLength: minLength(6),
                maxLength: maxLength(50)
            },
            password: {
                required,
                minLength: minLength(6),
                maxLength: maxLength(20)
            }
        }
    },
    methods: {
        showMessageModal() {
            this.$modal.show('messageModal');
        },
        hideMessageModal() {
            this.$modal.hide('messageModal');
        },
        login() {
            this.$v.$touch();

            if (this.$v.$invalid) {
                this.message = 'Моля попълнете формата коректно!';
                this.messageClass = 'errorMessage';

                this.showMessageModal();
                setTimeout(() => {
                    this.hideMessageModal()
                }, 3000)

            } else {
                this.$store.dispatch('authenticate/login', this.user).then(
                    () => {
                        this.$router.push({name: 'Home'});
                    },
                    error => {
                        this.message = error.response.data;
                        this.messageClass = 'errorMessage';

                        this.showMessageModal();
                        setTimeout(() => {
                            this.hideMessageModal()
                        }, 3000)
                    }
                );
            }
        },
        closeLoginForm() {
            this.$router.push({name: 'Home'})
        }
    },
    computed: {
        loggedIn() {
            return this.$store.state.authenticate.status.loggedIn;
        }
    },
    created() {
        if (this.loggedIn) {
            this.$router.push({name: 'movies'})
        }
    }
}