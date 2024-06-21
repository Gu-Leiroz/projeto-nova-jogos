import React from 'react';

import './styles.css';
import { FiArrowLeft } from 'react-icons/fi';

import logoImg from '../../assets/cogumelo.png';


export default function newJogo() {

    return (
        <div className="new-jogo-container">
            <div className="content">
                <section className="form">
                    <img src={logoImg} alt="Nova" />
                    <h1>Add Novo Jogo</h1>
                    <p>Coloque as infos e clique em "Add"!</p>
                    <a className="back-link" href="/jogos">
                        <FiArrowLeft size={16} color="#191970" />
                        Home
                    </a>
                </section>
                <form>
                    <input placeholder="Name"/>
                    <input placeholder="Price"/>
                    <input placeholder="Gender"/>
                    <input placeholder="Description"/>
                    <input placeholder="Rating" />

                    <button className="button" type="submit">Add</button>
                </form>
            </div>
        </div>
    
    
    )
}