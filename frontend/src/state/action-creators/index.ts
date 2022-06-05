import axios from 'axios';
import { Dispatch } from 'redux';
import { ActionType } from '../action-types';
import { Action } from '../actions';

export const searchRepositories = (term: string) => {
  return async (dispatch: Dispatch<Action>) => {
    dispatch({
      type: ActionType.SEARCH_REPOSITORIES,
    });

    try {
      const { data } = await axios.get(
        'https://registry.npmjs.org/-/v1/search',
        {
          params: {
            text: term,
          },
        }
      );

      const names = data.objects.map((result: any) => {
        return result.package.name;
      });

      dispatch({
        type: ActionType.SEARCH_REPOSITORIES_SUCCESS,
        payload: names,
      });
    } catch (err) {
      dispatch({
        type: ActionType.SEARCH_REPOSITORIES_ERROR,
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
    } catch (err) {
      dispatch({
        type: ActionType.AUTHENTICATE_ERROR,
        payload: err.message,
      });
    }
  };
};
