import React, { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './styles.css';
import api from '../../services/api'
import { FiPower, FiEdit, FiTrash2 } from 'react-icons/fi';

import logoImg from '../../assets/cogumelo.png'



export default function Jogos() {

    const [jogos, setJogos] = useState([]);

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');

    const navigate = useNavigate();

    async function logout() {
        localStorage.clear();
        navigate('/');
    }

    async function editJogos(id) {
        try {
            navigate(`jogos/new/${id}`)
        } catch (error) {
            alert('Edição falhou, tente novamente!');
        }
    }
    async function deletaJogos(id) {
        try {
            await api.delete(`api/postagem/v1/${id}`, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            })

            setJogos(jogos.filter(jogo => jogo.id !== id))
        } catch (err) {
            alert('Delete falhou! Tente Novamente.');
        }
    }

    async function carregarMaisJogos() {
        const response = await api.get('api/postagem/v1', {
            headers: {
                Authorization: `Bearer ${accessToken}`
            },

    });

        setJogos([...jogos, ...response.data._embedded.jogosVoes])
    }

    useEffect(() => {
        carregarMaisJogos();
    })

    return (
        <div className="jogos-container">
            <header>
                <img src={logoImg} alt="Projeto-nova" />
                <span>Bem Vindo, <strong>Gustavo</strong>!</span>
                <a className="button" href="jogo/new">Add novo Jogo</a>
                <button onClick={logout} type="button">
                    <FiPower size={18} color="#191970" />
                </button>
            </header>

            <h1>Posts</h1>
            <ul>
                {jogos.map(jogo => (
                    <li key={jogo.id}>
                        <strong>Name:</strong>
                        <p>{jogo.nome}</p>
                        <strong>Price:</strong>
                        <p>{Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(jogo.valor)}</p>
                        <strong>Gender:</strong>
                        <p>{jogo.genero}</p>
                        <strong>Description:</strong>
                        <p>{jogo.descricao}</p>
                        <strong>Rating:</strong>
                        <p>{jogo.nota}</p>


                        <button onClick={() => editJogos(jogo.id)} type="button">
                            <FiEdit size={20} color="#191970" />
                        </button>

                        <button onClick={() => deletaJogos(jogo.id)} type="button">
                            <FiTrash2 size={20} color="#191970" />
                        </button>
                    </li>
                ))}
            </ul>

            <button className="button" onClick={carregarMaisJogos} type="button">Carregar Mais</button>
        </div>
    );
}