import React from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import PropTypes from 'prop-types';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';

function Alert(props) {
  return <MuiAlert elevation={6} variant="outlined" {...props} />;
}

const useStyles = makeStyles(theme => ({
  appBarTitle: {
    flexGrow: 1,
  },
}));

class LandingPage extends React.Component {
  state = {
    alert: {
      severity: 'success',
      message: '',
      openAlert: false
    }
  }

  handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    this.setState({
      alert: {
        severity: 'success',
        message: '',
        openAlert: false
      }
    })
  };

  render() {
    const { classes } = this.props;

    return (
      <div>
        <AppBar position="static" color="primary">
          <Toolbar>
            <Typography variant="h6" className={classes.appBarTitle}>
              Spring Boot Application
          </Typography>
          </Toolbar>
        </AppBar>
        <Snackbar
          open={this.state.alert.openAlert}
          autoHideDuration={6000}
          onClose={this.handleClose}
          anchorOrigin={{
            vertical: 'top',
            horizontal: 'center'
          }}>
          <Alert
            severity={this.state.alert.severity}
            onClose={this.handleClose}
            variant="filled">
            {this.state.alert.message}
          </Alert>
        </Snackbar>
        Landing page under development ....
    </div>
    );
  }
}

LandingPage.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(useStyles)(LandingPage);