import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api', // Change the baseURL to your Spring Boot API URL
    headers: {
        'Content-Type': 'application/json',
    },
});

// Include JWT token in headers if available
apiClient.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default apiClient;