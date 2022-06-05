import { useState } from 'react';
import { useTypedSelector } from '../hooks/useTypedSelector';
import { useActions } from '../hooks/useActions';
import { Button,Form } from 'react-bootstrap';

const Login: React.FC = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { authenticate } = useActions();
  const { data, error, loading } = useTypedSelector(
    (state) => state.repositories
  );

  const onSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log(username + " " + password)
    authenticate(username,password);
    console.log("submiting");
  };

  return (
    <div className="d-flex justify-content-center">
        <Form onSubmit={onSubmit}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Username</Form.Label>
                <Form.Control value={username} onChange={(e) => setUsername(e.target.value)} type="text" placeholder="Enter user" />
                <Form.Text className="text-muted">
                We'll never share your username with anyone else.
                </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control value={password} onChange={(e) => setPassword(e.target.value)} type="password" placeholder="Password" />
            </Form.Group>
            <Button variant="primary" type="submit">
                Login
            </Button>
        </Form>
    </div>
  );
};

export default Login;