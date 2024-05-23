import './App.css'
import EmployeeComponent from './components/EmployeeComponent'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import ListEmployeeComponent from './components/ListEmployeeComponent'
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import ListVacationTypeComponent from './components/ListVacationTypeComponent'
import VacationTypeComponent from './components/VacationTypeComponent'
import ViewEmployeeComponent from './components/ViewEmployeeComponent'
import VacationComponent from './components/VacationComponent'

function App() {
  
  return (
    <>
    <BrowserRouter>
        <HeaderComponent/>
          <Routes>
            {/*// http://localhost:3000 */}
            <Route path='/' element = {<ListEmployeeComponent/>}></Route>
            {/*// http://localhost:3000/employees */}
            <Route path='/employees' element = {<ListEmployeeComponent/>}></Route>
            {/*// http://localhost:3000/add-employee */}
            <Route path='/add-employee' element = {<EmployeeComponent/>}></Route>
            {/*// http://localhost:3000/edit-employee/1 */}
            <Route path='/edit-employee/:id' element = {<EmployeeComponent/>}></Route>
            {/*// http://localhost:3000/view-employee/1 */}
            <Route path='/view-employee/:id' element = {<ViewEmployeeComponent/>}></Route>
            {/*// http://localhost:3000/1/vacations */}
            <Route path='/:id/vacations' element = {<ViewEmployeeComponent/>}></Route>
            {/*// http://localhost:3000/employee/:employeeId/vacations/add */}
            <Route path='/employee/:employeeId/vacations/add' element = {<VacationComponent/>}></Route>
            {/*// http://localhost:3000/employee/:employeeId/vacations/edit/:id */}
            <Route path='/employee/:employeeId/vacations/edit/:id' element = {<VacationComponent/>}></Route>
            {/*// http://localhost:3000/vacation-types */}
            <Route path='/vacation-types' element = {<ListVacationTypeComponent/>}></Route>
            {/*// http://localhost:3000/add-vacation-type */}
            <Route path='/add-vacation-type' element = {<VacationTypeComponent/>}></Route>
            {/*// http://localhost:3000/edit-vacation-type/1 */}
            <Route path='/edit-vacation-type/:id' element = {<VacationTypeComponent/>}></Route>
          </Routes>
        <FooterComponent/>
    </BrowserRouter>
    </>
  )
}

export default App
