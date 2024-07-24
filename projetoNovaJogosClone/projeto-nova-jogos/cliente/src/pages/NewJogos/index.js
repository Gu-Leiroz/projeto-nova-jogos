import React, { useState, useEffect } from 'react';
import { useNavigate, Link, useParams } from 'react-router-dom';
import { FiArrowLeft } from 'react-icons/fi';

import api from '../../services/api';

import './styles.css';


import logoImg from '../../assets/cogumelo.png';




export default function NewJogos() {

    const [id, setId] = useState(null);
    const [nome, setNome] = useState('');
    const [valor, setValor] = useState('');
    const [genero, setGenero] = useState('');
    const [descricao, setDescricao] = useState('');
    const [nota, setNota] = useState('');

    const { jogoId } = useParams();

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');

    const navigate = useNavigate();

    async function carregaJogos() {

        const fetchData = async () => {

            try {
                const response = await api.get(`api/postagem/v1/${jogoId}`, {
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                    }
                })
                setId(response.data.id);
                setNome(response.data.nome);
                setValor(response.data.valor);
                setGenero(response.data.genero);
                setDescricao(response.data.descricao);
                setNota(response.data.nota);
            } catch (error) {
                alert('Erro em adiocionar Jogo! Tente Novamente!');

            };
            fetchData();
            navigate('/jogos');
        }
        }

    useEffect(() => {
        if (jogoId === '0') return;
        else carregaJogos();
    }, [jogoId])

    async function saveOrUpdate(e) {
        e.preventDefault();

        const data = {
            nome,
            valor,
            genero,
            descricao,
            nota,
        }

        try {
            if (jogoId === '0') {
                await api.post('api/postagem/v1', data, {
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                    }
                });
            } else {
                data.id = id;
                await api.put('api/jogo/v1', data, {
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                    }
                });
            }

            navigator('/jogos');
        } catch (err) {
            alert('Preenchimento errado! Tente Novamente!')
        }
    }

    return (
        <div className="new-jogo-container">
            <div className="content">
                <section className="form">
                    <img src={logoImg} alt="Logo" />
                    <h1>{jogoId === '0' ? 'Add New' : 'Update'} Jogo</h1>
                    <p>Entre nas informações do jogo {jogoId === '0' ? "'Add'" : "'Update'"}!</p>
                    <Link className="back-link" to="/jogos">
                        <FiArrowLeft size={16} color="#191970" />
                        Voltar pra Jogos
                    </Link>
                </section>
                <form onSubmit={saveOrUpdate}>
                    <input
                        placeholder="Name"
                        value={nome}
                        onChange={e => setNome(e.target.value)}
                    />
                    <input
                        placeholder="Price"
                        value={valor}
                        onChange={e => setValor(e.target.value)}
                    />
                    <input
                        type="Gender"
                        value={genero}
                        onChange={e => setGenero(e.target.value)}
                    />
                    <input
                        placeholder="Description"
                        value={descricao}
                        onChange={e => setDescricao(e.target.value)}
                    />
                    <input
                        placeholder="Rating"
                        value={nota}
                        onChange={e => setNota(e.target.value)}
                    />

                    <button className="button" type="submit">{jogoId === '0' ? 'Add' : 'Update'}</button>
                </form>
            </div>
        </div>
    );
}