import { ActionType } from '../action-types';

interface SearchRepositoriesAction {
  type: ActionType.SEARCH_REPOSITORIES;
}

interface SearchRepositoriesSuccessAction {
  type: ActionType.SEARCH_REPOSITORIES_SUCCESS;
  payload: string[];
}

interface SearchRepositoriesErrorAction {
  type: ActionType.SEARCH_REPOSITORIES_ERROR;
  payload: string;
}

interface AuthenticateAction {
  type: ActionType.AUTHENTICATE;
}

interface AuthenticateSuccessAction {
  type: ActionType.AUTHENTICATE_SUCCESS;
  payload: any;
}

interface AuthenticateErrorAction {
  type: ActionType.AUTHENTICATE_ERROR;
  payload: any;
}

interface GetAllUserAction {
  type: ActionType.GET_ALL_USER;
}

interface GetAllUserSuccessAction {
  type: ActionType.GET_ALL_USER_SUCCESS;
  payload: any;
}

interface GetAllUserErrorAction {
  type: ActionType.GET_ALL_USER_ERROR;
  payload: any;
}

export type Action =
  | SearchRepositoriesAction
  | SearchRepositoriesSuccessAction
  | SearchRepositoriesErrorAction
  | AuthenticateAction
  | AuthenticateSuccessAction
  | AuthenticateErrorAction 
  | GetAllUserAction
  | GetAllUserSuccessAction
  | GetAllUserErrorAction;
