import {email, maxLength, minLength, required} from "vuelidate/lib/validators";
import MessageModal from "@/components/modal/MessageModal";
import UserService from "@/services/user-service";

export default {
    name: "UserRegisterComponent",
    components: {
        MessageModal
    },
    data() {
        return {
            userService: UserService,
            submitted: false,
            successful: false,
            user: {
                email: null,
                password: null,
                fullName: null,
                dateOfBirth: null,
            },
            modal: {
                message: null,
                messageClass: null
            }
        }
    },
    validations: {
        user: {
            email: {
                required,
                email,
                minLength: minLength(6),
                maxLength: maxLength(50),
            },
            password: {
                required,
                minLength: minLength(6),
                maxLength: maxLength(20),
            },
            fullName: {
                required,
                minLength: minLength(4),
                maxLength: maxLength(50),
            },
            dateOfBirth: {
                required,
                // TODO validate past or present
            },
            gender: {
                required
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
        register() {
            this.$v.$touch();

            if (this.$v.$invalid) {
                this.modal.message = 'Моля попълнете формата коректно!';
                this.modal.messageClass = 'errorMessage';

                this.showMessageModal();
                setTimeout(() => {
                    this.hideMessageModal()
                }, 3000)

            } else {

                this.userService.register(this.user).then((resp) => {
                    if (resp.status === 'OK') {
                        this.$router.push({name: 'Login'})
                    } else {
                        this.modal.message = resp.message;
                        this.modal.messageClass = 'errorMessage';

                        this.showMessageModal();
                        setTimeout(() => {
                            this.hideMessageModal()
                        }, 3000)
                    }
                })
            }
        },
        closeRegisterForm() {
            this.$router.push({name: 'Home'})
        }
    },
}