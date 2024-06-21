import React from 'react';
import { BrowserRouter, Route, Switch} from 'react-router-dom';

import Login from './pages/Login';
import Jogos from './pages/Jogos';
import NewJogos from './pages/NewJogos';

export default function Routes() {
    return (
    <BrowserRouter>
        <Switch>
                <Route path= "/" exact component={Login} />
                <Route path="/jogos" component={Jogos} />
                <Route path="/jogo/new" component={NewJogos} />
        </Switch> 
    </BrowserRouter>
    )
}