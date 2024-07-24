/* eslint-disable import/no-anonymous-default-export */
import './Logo.css'
import logo from '../../assets/img/logoCorte.png'
import React from 'react'
import { Link } from 'react-router-dom'

export default props =>
    <aside className="logo">
        <Link to="/" className="">
            <img src={logo} alt="logo"/>
        </Link>
    </aside>