import React, {useEffect, useState} from 'react'
import { deleteEmployee, listEmployees } from '../services/EmployeeService'
import { useNavigate } from 'react-router-dom'

const ListEmployeeComponent = () => {

    const [employees, setEmployees] = useState([])
    const [searchTerm, setSearchTerm] = useState('');
    const [filteredEmployees, setFilteredEmployees] = useState([]);

    const navigator = useNavigate();

    useEffect(() => {
        getAllEmployees();
    },[])

    useEffect(() => {
        filterEmployees();
    }, [searchTerm, employees]);

    function getAllEmployees(){
        listEmployees().then((response) => {
            setEmployees(response.data);
        }).catch(error => {
            console.error(error);
        })
    }
    function addNewEmployee(){
        navigator('/add-employee')
    }

    function updateEmployee(id){
        navigator(`/edit-employee/${id}`)
    }

    function removeEmployee(id) {
        const confirmed = window.confirm('Are you sure you want to delete this employee?');
        if (confirmed) {
            deleteEmployee(id).then((response) => {
            getAllEmployees();
          }).catch(error => {
            console.error(error);
          });
        }
    }

    function filterEmployees() {
        const term = searchTerm.toLowerCase();
        const filtered = employees.filter(employee =>
            employee.firstName.toLowerCase().includes(term) ||
            employee.lastName.toLowerCase().includes(term) ||
            employee.email.toLowerCase().includes(term) ||
            employee.run.toLowerCase().includes(term)
        );
        setFilteredEmployees(filtered);
    }

    function goToVacationTypes() {
        navigator('/vacation-types');
    }

    function viewEmployee(id) {
        navigator(`/view-employee/${id}`);
    }

  return (
    <div className='container'>
        <h2 className='text-center'>List of Employees</h2>
        <button className='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>
        <button className='btn btn-secondary mb-2' onClick={goToVacationTypes} style={{ marginLeft: '10px' }}>Manage Vacation Types</button>
        <input
                    type="text"
                    placeholder="Search by Name, Email, or R.U.N"
                    className="form-control mt-2"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
        />
        <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th>Employee Id</th>
                    <th>Employee First Name</th>
                    <th>Employee Last Name</th>
                    <th>Employee Email</th>
                    <th>Employee R.U.N</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    filteredEmployees.map(employee =>
                        <tr key = {employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.email}</td>
                            <td>{employee.run}</td>
                            <td>
                                <button className='btn btn-info' onClick={() => viewEmployee(employee.id)}>View</button>
                                <button className='btn btn-warning' onClick={() => updateEmployee(employee.id)} style={{ marginLeft: '10px' }}>Update</button>
                                <button className='btn btn-danger' onClick={() => removeEmployee(employee.id)} style={{marginLeft: '10px'}}>Delete</button>
                            </td>
                        </tr>
                    )
                }
            </tbody>
        </table>
    </div>
  )
}

export default ListEmployeeComponent