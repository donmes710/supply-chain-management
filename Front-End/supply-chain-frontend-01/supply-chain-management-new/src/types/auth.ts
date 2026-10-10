export interface LoginCredentials {
  username: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  user: {
    id: string;
    username: string;
    role: 'admin' | 'manager';
  };
}

export interface ApiError {
  message: string;
  status?: number;
}
