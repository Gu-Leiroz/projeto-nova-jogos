import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Login from './pages/Login';
import Jogos from './pages/Jogos';
import NewJogos from './pages/NewJogos';

export default function AppRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" exact element={<Login />} />
                <Route path="/jogos" element={<Jogos />} />
                <Route path="/jogo/new/" element={<NewJogos />} />
            </Routes>
        </BrowserRouter>
    );
}