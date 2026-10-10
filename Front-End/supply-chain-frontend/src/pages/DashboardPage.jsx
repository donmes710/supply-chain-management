import React, { useEffect, useState } from 'react';
import { useAuth } from '../hooks/useAuth';
import { getSuppliers } from '../services/api';

const DashboardPage = () => {
    const { logout } = useAuth();
    const [suppliers, setSuppliers] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
    const fetchSuppliers = async () => {
        try {
            const response = await getSuppliers();
            setSuppliers(response.data.data);
        } catch (err) {
            setError('Failed to fetch suppliers. Your token may be invalid or expired.');
            console.error(err); // <-- ADD THIS LINE
        }
    };
    fetchSuppliers();
}, []);

    return (
        <div>
            <nav style={{ display: 'flex', justifyContent: 'space-between', padding: '1rem', background: '#eee' }}>
                <h1>Dashboard</h1>
                <button onClick={logout}>Logout</button>
            </nav>
            <main style={{ padding: '1rem' }}>
                <h2>Welcome!</h2>
                <h3>Suppliers List (Protected Data)</h3>
                {error && <p style={{ color: 'red' }}>{error}</p>}
                <ul>
                    {suppliers && suppliers.map(supplier => (
                        <li key={supplier.id}>{supplier.name} - {supplier.companyName}</li>
                    ))}
                </ul>
            </main>
        </div>
    );
};

export default DashboardPage;