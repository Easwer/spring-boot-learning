import React from 'react';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import TitleBar from '../Components/TitleBar';
import PropTypes from 'prop-types';

const useStyles = makeStyles(theme => ({
}));

class LandingPage extends React.Component {
  render() {
    return (
      <div>
        <TitleBar/ >
        Landing Page Under Construction....
      </div>
    );
  }
}

LandingPage.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(useStyles)(LandingPage);