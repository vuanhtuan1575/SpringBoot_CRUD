import { ActionType } from '../action-types';
import { Action } from '../actions';

interface RepositoriesState {
  loading: boolean;
  error: string | null;
  data: string[];
  token: string | null;
  users: object[];
  loadingUser:boolean;
  userError:string[] | null;
}

const initialState = {
  loading: false,
  error: null,
  data: [],
  token:null,
  loadingUser:false,
  userError:null,
  users:[]
};

const reducer = (
  state: RepositoriesState = initialState,
  action: Action
): RepositoriesState => {
  switch (action.type) {
    case ActionType.SEARCH_REPOSITORIES:
      return {...initialState,loading: true, error: null, data: [] };
    case ActionType.SEARCH_REPOSITORIES_SUCCESS:
      return {...initialState, loading: false, error: null, data: action.payload };
    case ActionType.SEARCH_REPOSITORIES_ERROR:
      return {...initialState, loading: false, error: action.payload, data: [] };
    case ActionType.AUTHENTICATE:
      return {...initialState, loading: true, error: null };
    case ActionType.AUTHENTICATE_SUCCESS:
      return {...initialState, loading: false, error: null, token: action.payload.result};
    case ActionType.SEARCH_REPOSITORIES_ERROR:
      return {...initialState, loading: false, error: action.payload }; 
    case ActionType.GET_ALL_USER:
      return {...initialState, loadingUser: true, userError: null };
    case ActionType.GET_ALL_USER_SUCCESS:
      return {...initialState, loadingUser: false, userError: null, users: action.payload};
    case ActionType.GET_ALL_USER_ERROR:
      return {...initialState, loadingUser: false, userError: action.payload }; 
    default:
      return state;
  }
};

export default reducer;
