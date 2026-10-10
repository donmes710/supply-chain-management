import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { login as apiLogin } from '../services/api';
import { AuthContext } from './auth-context';

export const AuthProvider = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            setIsAuthenticated(true);
        }
    }, []);

    const login = async (username, password) => {
        try {
            const response = await apiLogin(username, password);
            localStorage.setItem('token', response.data.token);
            setIsAuthenticated(true);
            navigate('/dashboard');
        } catch (error) {
            console.error('Login failed:', error);
            // Optionally: set an error state to show a message to the user
        }
    };

    const logout = () => {
        localStorage.removeItem('token');
        setIsAuthenticated(false);
        navigate('/login');
    };

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};