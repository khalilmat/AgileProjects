import React, { Component } from "react";
import CreateProjectButton from "./project/CreateProjectButton";
import ProjectItem from "./project/ProjectItem";
import { connect } from "react-redux";
import { getProjects } from "../actions/projectActions";
import PropTypes from "prop-types";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getProjects();
  }

  render() {
    const { projects } = this.props;

    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <CreateProjectButton />

              <br />
              <hr />
              {projects.map((project) => (
                <ProjectItem key={project.id} project={project} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

//wire project to Dashbaord
Dashboard.propTypes = {
  project: PropTypes.object.isRequired,
  getProjects: PropTypes.func.isRequired,
};

//map state to props to wire project
const mapStateToProps = (state) => ({
  project: state.project,
});

//get Projects when loading Dashboard
export default connect(mapStateToProps, { getProjects })(Dashboard);
