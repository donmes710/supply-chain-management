import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080',
});

// Automatically add the Authorization header to every request
apiClient.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

export const login = (username, password) => {
    return apiClient.post('/api/auth/login', { username, password });
};

export const getSuppliers = () => {
    return apiClient.get('/suppliers');
};

// You can add all other backend calls here (getProducts, saveCustomer, etc.)