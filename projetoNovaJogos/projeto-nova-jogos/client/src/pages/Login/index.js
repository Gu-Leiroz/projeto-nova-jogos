import React from 'react';
import './styles.css';

import logoImg from '../../assets/cogumelo.png'


export default function Login() {
    return (

        <div className="login-conteiner">

            <section className="form">
                <img src={logoImg} alt="Logo" />
                <form>
                    <h1>Acesse sua conta</h1>
                    <input placeholder="Username" />
                    <input type="password" placeholder="Password" />


                    <button className="button" type="submit">Login</button>
                </form>

            </section>

            

        </div>
       

    )
}