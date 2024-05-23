import React, { useEffect, useState } from 'react'
import {deleteVacationType, listVacationTypes} from '../services/VacationTypeService'
import { useNavigate } from 'react-router-dom'

const ListVacationTypeComponent = () => {

    const [vacationTypes, setVacationTypes] = useState([])

    const navigator = useNavigate();

    useEffect(() => {
        getAllVacationTypes();
    }, [])

    function getAllVacationTypes(){
        listVacationTypes().then((response) => {
            setVacationTypes(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function addNewVacationType(){
        navigator('/add-vacation-type')
    }

    function updateVacationType(id){
        navigator(`/edit-vacation-type/${id}`)
    }
    function removeVacationType(id){
        const confirmed = window.confirm('Are you sure you want to delete this vacation type?');
        if(confirmed){
            deleteVacationType(id).then((response) => {
                getAllVacationTypes();
            }).catch(error => {
                console.error(error);
            });
        }
    }

    function goToEmployees(){
        navigator('/employees');
    }

  return (
    <div className='container'>
        <h2 className='text-center'>List of Vacation Types</h2>
        <button className='btn btn-primary mb-2' onClick={addNewVacationType}>Add Vacation Type</button>
        <button className='btn btn-secondary mb-2' onClick={goToEmployees} style={{ marginLeft: '10px' }}>Manage Employees</button>

        <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th>Vacation Type Id</th>
                    <th>Vacation Type Description</th>
                    <th>Vacation Type Max Days</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    vacationTypes.map(vacationType =>
                        <tr key = {vacationType.id}>
                            <td>{vacationType.id}</td>
                            <td>{vacationType.description}</td>
                            <td>{vacationType.maxDays}</td>
                            <td>
                                <button className='btn btn-warning' onClick={() => updateVacationType(vacationType.id)}>Update</button>
                                <button className='btn btn-danger' onClick={() => removeVacationType(vacationType.id)} style={{marginLeft: '10px'}}>Delete</button>
                            </td>
                        </tr>
                        
                    )
                }
            </tbody>
        </table>
    </div>
  )
}

export default ListVacationTypeComponent