const formLogin = document.querySelector("form");

const Iusername = document.querySelector(".username");
const Ipassword = document.querySelector(".password");

function Login(event) {
    event.preventDefault(); // Prevenir o comportamento padrão do formulário

    fetch("http://localhost:8080/auth/signin", {
        headers: {
            'Content-type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            username: Iusername.value,
            password: Ipassword.value,
        })
    })
        .then(function (res) {
            if (!res.ok) {
                throw new Error('Erro na requisição');
            }
            return res.json();
        })
        .then(function (data) {
            console.log(data); // Verifique o objeto retornado pela API
            const token = data.token; // Supondo que o token está no campo "token"
            console.log('Token:', token);
            limpar();

  
            localStorage.setItem('accessToken', token);
        })
        .catch(function (error) {
            console.error(error);
        });
};

function limpar() {
    Iusername.value = "";
    Ipassword.value = "";
}

formLogin.addEventListener('submit', Login);
