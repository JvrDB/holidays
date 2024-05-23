import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { getEmployee, getVacationsByEmployeeId } from '../services/EmployeeService';
import { deleteVacation } from '../services/VacationService';

const ViewEmployeeComponent = () => {
    const { id } = useParams(); // id refers to employeeId
    const [employee, setEmployee] = useState({});
    const [vacations, setVacations] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getEmployee(id).then((response) => {
            setEmployee(response.data);
        }).catch(error => {
            console.error(error);
        });

        getVacations(id);
    }, [id]);

    function getVacations(employeeId) {
        getVacationsByEmployeeId(employeeId).then((response) => {
            setVacations(response.data);
        }).catch(error => {
            console.error(error);
        });
    }

    function addNewVacation() {
        navigate(`/employee/${id}/vacations/add`);
    }

    function updateVacation(vacationId) {
        navigate(`/employee/${id}/vacations/edit/${vacationId}`);
    }

    function removeVacation(vacationId) {
        const confirmed = window.confirm('Are you sure you want to delete this vacation?');
        if (confirmed) {
            deleteVacation(vacationId).then(() => {
                getVacations(id);
            }).catch(error => {
                console.error(error);
            });
        }
    }

    return (
        <div className='container'>
            <br />
            <h2 className='text-center'>Employee Details</h2>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    <div className='card-body'>
                        <div className='row'>
                            <div className='col'>
                                <p className='card-text'>First Name: {employee.firstName}</p>
                            </div>
                            <div className='col'>
                                <p className='card-text'>Last Name: {employee.lastName}</p>
                            </div>
                        </div>
                        <div className='row'>
                            <div className='col'>
                                <p className='card-text'>Email: {employee.email}</p>
                            </div>
                            <div className='col'>
                                <p className='card-text'>R.U.N: {employee.run}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br />
            <h2 className='text-center'>Vacations</h2>
            <button className='btn btn-primary mb-2' onClick={addNewVacation}>Add Vacation</button>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Vacation Id</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Vacation Type</th>
                        <th>Vacation Max Days</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        vacations.map(vacation =>
                            <tr key={vacation.id}>
                                <td>{vacation.id}</td>
                                <td>{vacation.startDate}</td>
                                <td>{vacation.endDate}</td>
                                <td>{vacation.vacationType.description}</td>
                                <td>{vacation.vacationType.maxDays}</td>
                                <td>
                                    <button className='btn btn-warning' onClick={() => updateVacation(vacation.id)}>Update</button>
                                    <button className='btn btn-danger' onClick={() => removeVacation(vacation.id)} style={{ marginLeft: '10px' }}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    );
}

export default ViewEmployeeComponent;