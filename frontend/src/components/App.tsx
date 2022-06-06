import { Provider } from 'react-redux';
import { store } from '../state';
import Login from './Login';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Routes, Route, Link } from "react-router-dom";
import Admin from './Admin';

const App = () => {
  return (
    <Provider store={store}>
      <div>
      <Routes>
        <Route path="/admin" element={<Admin />} />
        <Route path="/login" element={<Login />} />
      </Routes>
      </div>
    </Provider>
  );
};

export default App;
