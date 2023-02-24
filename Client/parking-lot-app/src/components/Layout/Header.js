import classes from './Header.module.css'
import logo from '../../assets/Logo.svg';
import Navbar from "../Navbar/Navbar";

const Header = (props) => {
    return (
        <>
            <div className={classes.header}>
                <img src={logo} className={classes.logo} alt={"LOGO"}/>
            </div>
            <Navbar />
        </>

    )
}
export default Header