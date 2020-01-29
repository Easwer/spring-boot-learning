import React from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import PropTypes from 'prop-types';
import Link from '@material-ui/core/Link';
import Box from '@material-ui/core/Box';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import LandingPage from '../LandingPage/LandingPage';
import Cookies from 'universal-cookie';

function Alert(props) {
    return <MuiAlert elevation={6} variant="outlined" {...props} />;
}

function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary">
            {'Copyright © '}
            <Link color="inherit" href="https://material-ui.com/">
                Easwer
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const useStyles = makeStyles(theme => ({
    paper: {
        marginTop: 200,
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.primary.main,
    },
    form: {
        width: '100%',
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
    root: {
        flexGrow: 1,
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        flexGrow: 1,
    },
}));

class Login extends React.Component {
    state = {
        username: '',
        password: '',
        alert: {
            severity: 'success',
            message: '',
            openAlert: false
        }
    }

    login(event) {
        var me = this;
        $.ajax({
            url: '/login?username=' + this.state.username + '&password=' + this.state.password,
            success: function (result) {
                if (result && result.message) {
                    me.setState({
                        alert: {
                            message: result.message,
                            severity: 'success',
                            openAlert: true
                        }
                    })
                    const cookies = new Cookies();
                    cookies.set("authToken", result.object.authToken, { path: "/" });
                    if (result.object && result.object.user) {
                        localStorage.setItem("userDetails", result.object.user);
                    }
                    ReactDOM.render(<LandingPage />, document.getElementById('root'));
                }
            },
            error: function (result) {
                if (result && result.responseJSON && result.responseJSON.message) {
                    me.setState({
                        alert: {
                            message: result.responseJSON.message,
                            severity: 'error',
                            openAlert: true
                        }
                    })
                } else if (result && result.statusText) {
                    me.setState({
                        alert: {
                            message: result.statusText,
                            severity: 'error',
                            openAlert: true
                        }
                    })
                } else {
                    me.setState({
                        alert: {
                            message: 'Authentication Failure.',
                            severity: 'error',
                            openAlert: true
                        }
                    })
                }
            }
        });
    }

    handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        this.setState({
            alert: {
                openAlert: false,
                severity: 'success',
                message: ''
            }
        })
    };

    render() {
        const { classes } = this.props;
        return (
            <div>
                <AppBar position="static" color="primary">
                    <Toolbar>
                        <Typography variant="h6" className={classes.title}>
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
                <Container component="main" maxWidth="xs">
                    <div className={classes.paper}>
                        <Typography align="center" variant="h5" color="primary">
                            Login
                        </Typography>
                        <form className={classes.form} noValidate>
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="email"
                                label="Email Address"
                                name="email"
                                autoComplete="email"
                                value={this.state.username}
                                onChange={(e) => this.setState({
                                    username: e.target.value
                                })}
                                autoFocus
                            />
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                name="password"
                                label="Password"
                                type="password"
                                id="password"
                                value={this.state.password}
                                onChange={(e) => this.setState({
                                    password: e.target.value
                                })}
                                autoComplete="current-password"
                            />
                            <Button
                                fullWidth
                                variant="contained"
                                color="primary"
                                className={classes.submit}
                                onClick={(event) => this.login(event)}>
                                Login
                            </Button>
                        </form>
                    </div>
                    <Box mt={8}>
                        <Copyright />
                    </Box>
                </Container>
            </div>
        )
    }
}

Login.propTypes = {
    classes: PropTypes.object.isRequired,
};
export default withStyles(useStyles)(Login);