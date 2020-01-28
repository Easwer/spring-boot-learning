import React from 'react';
import ReactDOM from 'react-dom';
import Cookies from 'universal-cookie';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import PropTypes from 'prop-types';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import Login from '../Login/Login';
import MenuIcon from '@material-ui/icons/Menu';
import AccountCircle from '@material-ui/icons/AccountCircle';
import IconButton from '@material-ui/core/IconButton';
import MenuItem from '@material-ui/core/MenuItem';
import Menu from '@material-ui/core/Menu';

function Alert(props) {
  return <MuiAlert elevation={6} variant="outlined" {...props} />;
}

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
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
    },
    userMenu: {
      anchorEl: null,
      open: false
    }
  }

  handleAlertClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    this.setState({
      alert: {
        severity: 'success',
        message: '',
        openAlert: false
      }
    });
  }

  handleMenu = event => {
    this.setState({
      userMenu: {
        anchorEl: event.currentTarget,
        open: true
      }
    });
  }

  handleMenuClose = event => {
    this.setState({
      userMenu: {
        anchorEl: event.currentTarget,
        open: false
      }
    });
  }

  logout = event => {
    const authCookie = new Cookies();
    authCookie.remove("authToken");
    localStorage.clear();
    ReactDOM.render(<Login />, document.getElementById('root'));
  }

  render() {
    const { classes } = this.props;
    const cookies = new Cookies();
    const cookie = cookies.get("authToken")
    if (!cookie) {
      ReactDOM.render(<Login />, document.getElementById('root'));
      return;
    }

    return (
      <div className={classes.root}>
        <AppBar position="static" color="primary">
          <Toolbar>
            <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
              <MenuIcon />
            </IconButton>
            <Typography variant="h6" className={classes.appBarTitle}>
              Spring Boot Application
            </Typography>
            <div>
              <IconButton
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={this.handleMenu}
                color="inherit">
                <AccountCircle />
              </IconButton>
              <Menu
                id="menu-appbar"
                anchorEl={this.state.userMenu.anchorEl}
                anchorOrigin={{
                  vertical: 'top',
                  horizontal: 'right',
                }}
                keepMounted
                transformOrigin={{
                  vertical: 'top',
                  horizontal: 'right',
                }}
                open={this.state.userMenu.open}
                onClose={this.handleMenuClose}>
                <MenuItem onClick={this.logout}>Logout</MenuItem>
              </Menu>
            </div>
          </Toolbar>
        </AppBar>
        <Snackbar
          open={this.state.alert.openAlert}
          autoHideDuration={6000}
          onClose={this.handleAlertClose}
          anchorOrigin={{
            vertical: 'top',
            horizontal: 'center'
          }}>
          <Alert
            severity={this.state.alert.severity}
            onClose={this.handleAlertClose}
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