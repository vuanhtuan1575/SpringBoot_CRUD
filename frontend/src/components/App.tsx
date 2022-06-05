import { Provider } from 'react-redux';
import { store } from '../state';
import Login from './Login';
import RepositoriesList from './RepositoriesList';
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => {
  return (
    <Provider store={store}>
      <div>
        <h1>Search For a Package</h1>
        <RepositoriesList />
        <Login/>
      </div>
    </Provider>
  );
};

export default App;
