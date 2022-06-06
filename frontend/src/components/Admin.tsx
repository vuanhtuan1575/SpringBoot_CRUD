import { useState ,useEffect} from 'react';
import { useTypedSelector } from '../hooks/useTypedSelector';
import { useActions } from '../hooks/useActions';
import { Navigate } from 'react-router-dom';
import { Table} from 'react-bootstrap';

const Admin: React.FC = () => {
  const [term, setTerm] = useState('');
  const { getAllUser } = useActions();
  const {users, error, loading } = useTypedSelector(
    (state) => state.repositories
  );


  useEffect(() => {
    getAllUser();
  },[]);
  console.log(users);
  const token = localStorage.getItem('token');
  if(!token){
    alert(`You aren't login, please login`)
    return (
      <Navigate to="/login" replace={true} />
    )
  }
  else
  return (
<Table striped bordered hover>
  <thead>
    <tr>
      <th>NAME</th>
      <th>USERNAME</th>
      <th>PHONE</th>
      <th>ROLE</th>
    </tr>
  </thead>
  <tbody>
    
        {users.map((item:any,i:any)=>(
    <tr>
      <td>{item.name}</td>
      <td>{item.username}</td>
      <td>{item.phone}</td>
      <td>{item.roleName}</td>
    </tr>))}
      
  </tbody>
</Table>
  );
};

export default Admin;
