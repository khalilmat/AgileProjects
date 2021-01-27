import { GET_PROJECTS } from "../actions/types";

const initialState = {
  projects: [], //array of projects
  project: {}, //single project
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_PROJECTS:
      return {
        ...state, //return the state
        projects: action.payload, //load the projects with the payload we are getting from the server
      };
    default:
      return state;
  }
}
