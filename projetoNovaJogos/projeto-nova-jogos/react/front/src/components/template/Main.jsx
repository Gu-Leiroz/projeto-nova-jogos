/* eslint-disable import/no-anonymous-default-export */
import './Main.css'
import React from 'react'
import Header from './Header'

export default props =>
    <React.Fragment>
        <Header {...props} />
        <main className="content conteiner-fluid">
            <div className="p-3 mt-0">
                {props.children}
            </div>
        </main>

    </React.Fragment>
