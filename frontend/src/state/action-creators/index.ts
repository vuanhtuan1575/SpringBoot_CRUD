import axios from 'axios';
import { Dispatch } from 'redux';
import { ActionType } from '../action-types';
import { Action } from '../actions';

export const getAllUser = () => {
  return async (dispatch: Dispatch<Action>) => {
    dispatch({
      type: ActionType.GET_ALL_USER,
    });

    try {
      const { data } = await axios.get(
        'http://localhost:8082/api/user'
      );

      dispatch({
        type: ActionType.GET_ALL_USER_SUCCESS,
        payload: data,
      });
    } catch (err:any) {
      dispatch({
        type: ActionType.GET_ALL_USER_ERROR,
        payload: err.message,
      });
    }
  };
};

export const authenticate = (username: string,password:string) => {
  return async (dispatch: Dispatch<Action>) => {
    dispatch({
      type: ActionType.AUTHENTICATE,
    });

    try {
     
      const authen = await axios.post(
        `http://localhost:8082/api/auth/authenticate`,
        {
          "username":username,
          "password":password
        }
      );
      console.log(authen);
      const { data } = authen;
      console.log(data);

      // const responseLogin = data.objects.map((result: any) => {
      //   return result.package.name;
      // });

      dispatch({
        type: ActionType.AUTHENTICATE_SUCCESS,
        payload: data,
      });
    } catch (err:any) {
      dispatch({
        type: ActionType.AUTHENTICATE_ERROR,
        payload: err.message,
      });
    }
  };
};
