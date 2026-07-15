import { FormEvent, useEffect, useState } from 'react'
import './App.css'

const API_URL = 'http://localhost:8080'

type Mission = {
  id: number
  name: string
  description: string
  createdAt: string
}

type Waypoint = {
  id: number
  latitude: number
  longitude: number
  altitude: number
  orderNumber: number
}

type ApiError = {
  message?: string
  errors?: string[]
}

// Main application component
function App() {
  const [missions, setMissions] = useState<Mission[]>([])
  const [selectedMission, setSelectedMission] = useState<Mission | null>(null)
  const [waypoints, setWaypoints] = useState<Waypoint[]>([])

  const [missionName, setMissionName] = useState('')
  const [missionDescription, setMissionDescription] = useState('')
  const [editingMissionId, setEditingMissionId] = useState<number | null>(null)

  const [latitude, setLatitude] = useState('')
  const [longitude, setLongitude] = useState('')
  const [altitude, setAltitude] = useState('')
  const [orderNumber, setOrderNumber] = useState('')
  const [editingWaypointId, setEditingWaypointId] = useState<number | null>(null)

  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  // Load missions when the application starts
  useEffect(() => {
    void loadMissions()
  }, [])

  // Read an API error response
  async function readError(response: Response): Promise<string> {
    try {
      const data = (await response.json()) as ApiError

      if (data.errors?.length) {
        return data.errors.join(', ')
      }

      return data.message ?? 'Request failed'
    } catch {
      return `Request failed with status ${response.status}`
    }
  }

  // Load all missions
  async function loadMissions(): Promise<void> {
    setLoading(true)
    setError('')

    try {
      const response = await fetch(`${API_URL}/missions`)

      if (!response.ok) {
        throw new Error(await readError(response))
      }

      const data = (await response.json()) as Mission[]
      setMissions(data)
    } catch (requestError) {
      setError(
          requestError instanceof Error
              ? requestError.message
              : 'Could not load missions',
      )
    } finally {
      setLoading(false)
    }
  }

  // Load waypoints for the selected mission
  async function loadWaypoints(mission: Mission): Promise<void> {
    setSelectedMission(mission)
    setError('')

    try {
      const response = await fetch(
          `${API_URL}/missions/${mission.id}/waypoints`,
      )

      if (!response.ok) {
        throw new Error(await readError(response))
      }

      const data = (await response.json()) as Waypoint[]
      setWaypoints(data)
    } catch (requestError) {
      setError(
          requestError instanceof Error
              ? requestError.message
              : 'Could not load waypoints',
      )
    }
  }

  // Create or update a mission
  async function saveMission(event: FormEvent<HTMLFormElement>): Promise<void> {
    event.preventDefault()
    setError('')

    const isEditing = editingMissionId !== null
    const url = isEditing
        ? `${API_URL}/missions/${editingMissionId}`
        : `${API_URL}/missions`

    try {
      const response = await fetch(url, {
        method: isEditing ? 'PUT' : 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          name: missionName,
          description: missionDescription,
        }),
      })

      if (!response.ok) {
        throw new Error(await readError(response))
      }

      resetMissionForm()
      await loadMissions()
    } catch (requestError) {
      setError(
          requestError instanceof Error
              ? requestError.message
              : 'Could not save mission',
      )
    }
  }

  // Fill the mission form for editing
  function startMissionEdit(mission: Mission): void {
    setEditingMissionId(mission.id)
    setMissionName(mission.name)
    setMissionDescription(mission.description)
  }

  // Delete a mission
  async function deleteMission(missionId: number): Promise<void> {
    const confirmed = window.confirm(
        'Delete this mission and all its waypoints?',
    )

    if (!confirmed) {
      return
    }

    setError('')

    try {
      const response = await fetch(`${API_URL}/missions/${missionId}`, {
        method: 'DELETE',
      })

      if (!response.ok) {
        throw new Error(await readError(response))
      }

      if (selectedMission?.id === missionId) {
        setSelectedMission(null)
        setWaypoints([])
      }

      await loadMissions()
    } catch (requestError) {
      setError(
          requestError instanceof Error
              ? requestError.message
              : 'Could not delete mission',
      )
    }
  }

  // Create or update a waypoint
  async function saveWaypoint(
      event: FormEvent<HTMLFormElement>,
  ): Promise<void> {
    event.preventDefault()

    if (!selectedMission) {
      setError('Select a mission first')
      return
    }

    setError('')

    const isEditing = editingWaypointId !== null
    const url = isEditing
        ? `${API_URL}/waypoints/${editingWaypointId}`
        : `${API_URL}/missions/${selectedMission.id}/waypoints`

    try {
      const response = await fetch(url, {
        method: isEditing ? 'PUT' : 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          latitude: Number(latitude),
          longitude: Number(longitude),
          altitude: Number(altitude),
          orderNumber: Number(orderNumber),
        }),
      })

      if (!response.ok) {
        throw new Error(await readError(response))
      }

      resetWaypointForm()
      await loadWaypoints(selectedMission)
    } catch (requestError) {
      setError(
          requestError instanceof Error
              ? requestError.message
              : 'Could not save waypoint',
      )
    }
  }

  // Fill the waypoint form for editing
  function startWaypointEdit(waypoint: Waypoint): void {
    setEditingWaypointId(waypoint.id)
    setLatitude(String(waypoint.latitude))
    setLongitude(String(waypoint.longitude))
    setAltitude(String(waypoint.altitude))
    setOrderNumber(String(waypoint.orderNumber))
  }

  // Delete a waypoint
  async function deleteWaypoint(waypointId: number): Promise<void> {
    if (!selectedMission) {
      return
    }

    const confirmed = window.confirm('Delete this waypoint?')

    if (!confirmed) {
      return
    }

    setError('')

    try {
      const response = await fetch(`${API_URL}/waypoints/${waypointId}`, {
        method: 'DELETE',
      })

      if (!response.ok) {
        throw new Error(await readError(response))
      }

      await loadWaypoints(selectedMission)
    } catch (requestError) {
      setError(
          requestError instanceof Error
              ? requestError.message
              : 'Could not delete waypoint',
      )
    }
  }

  // Clear the mission form
  function resetMissionForm(): void {
    setEditingMissionId(null)
    setMissionName('')
    setMissionDescription('')
  }

  // Clear the waypoint form
  function resetWaypointForm(): void {
    setEditingWaypointId(null)
    setLatitude('')
    setLongitude('')
    setAltitude('')
    setOrderNumber('')
  }

  return (
      <div className="app">
        <header className="topbar">
          <div>
            <p className="eyebrow">UAV planning system</p>
            <h1>Drone Mission Planner</h1>
          </div>

          <div className="server-status">
            <span className="status-dot" />
            Backend: localhost:8080
          </div>
        </header>

        {error && <div className="error-message">{error}</div>}

        <main className="layout">
          <section className="panel missions-panel">
            <div className="panel-header">
              <div>
                <h2>Missions</h2>
                <p>Create and manage drone missions.</p>
              </div>

              <span className="count-badge">{missions.length}</span>
            </div>

            <form className="form" onSubmit={saveMission}>
              <label>
                Mission name
                <input
                    value={missionName}
                    onChange={(event) => setMissionName(event.target.value)}
                    placeholder="Forest inspection"
                />
              </label>

              <label>
                Description
                <textarea
                    value={missionDescription}
                    onChange={(event) =>
                        setMissionDescription(event.target.value)
                    }
                    placeholder="Describe the mission purpose"
                    rows={3}
                />
              </label>

              <div className="form-actions">
                <button className="primary-button" type="submit">
                  {editingMissionId ? 'Update mission' : 'Create mission'}
                </button>

                {editingMissionId && (
                    <button
                        className="secondary-button"
                        type="button"
                        onClick={resetMissionForm}
                    >
                      Cancel
                    </button>
                )}
              </div>
            </form>

            <div className="mission-list">
              {loading && <p className="empty-message">Loading missions...</p>}

              {!loading && missions.length === 0 && (
                  <p className="empty-message">No missions created yet.</p>
              )}

              {missions.map((mission) => (
                  <article
                      className={
                        selectedMission?.id === mission.id
                            ? 'mission-card selected'
                            : 'mission-card'
                      }
                      key={mission.id}
                  >
                    <button
                        className="mission-content"
                        type="button"
                        onClick={() => void loadWaypoints(mission)}
                    >
                      <strong>{mission.name}</strong>
                      <span>{mission.description}</span>
                    </button>

                    <div className="card-actions">
                      <button
                          type="button"
                          onClick={() => startMissionEdit(mission)}
                      >
                        Edit
                      </button>

                      <button
                          className="danger-text"
                          type="button"
                          onClick={() => void deleteMission(mission.id)}
                      >
                        Delete
                      </button>
                    </div>
                  </article>
              ))}
            </div>
          </section>

          <section className="panel waypoints-panel">
            <div className="panel-header">
              <div>
                <h2>Waypoints</h2>
                <p>
                  {selectedMission
                      ? `Selected mission: ${selectedMission.name}`
                      : 'Select a mission to manage its route.'}
                </p>
              </div>

              <span className="count-badge">{waypoints.length}</span>
            </div>

            {selectedMission ? (
                <>
                  <form className="waypoint-form" onSubmit={saveWaypoint}>
                    <label>
                      Latitude
                      <input
                          type="number"
                          step="any"
                          value={latitude}
                          onChange={(event) => setLatitude(event.target.value)}
                          placeholder="54.6872"
                          required
                      />
                    </label>

                    <label>
                      Longitude
                      <input
                          type="number"
                          step="any"
                          value={longitude}
                          onChange={(event) => setLongitude(event.target.value)}
                          placeholder="25.2797"
                          required
                      />
                    </label>

                    <label>
                      Altitude
                      <input
                          type="number"
                          step="any"
                          value={altitude}
                          onChange={(event) => setAltitude(event.target.value)}
                          placeholder="50"
                          required
                      />
                    </label>

                    <label>
                      Order
                      <input
                          type="number"
                          min="1"
                          value={orderNumber}
                          onChange={(event) => setOrderNumber(event.target.value)}
                          placeholder="1"
                          required
                      />
                    </label>

                    <div className="form-actions waypoint-actions">
                      <button className="primary-button" type="submit">
                        {editingWaypointId
                            ? 'Update waypoint'
                            : 'Add waypoint'}
                      </button>

                      {editingWaypointId && (
                          <button
                              className="secondary-button"
                              type="button"
                              onClick={resetWaypointForm}
                          >
                            Cancel
                          </button>
                      )}
                    </div>
                  </form>

                  <div className="table-wrapper">
                    <table>
                      <thead>
                      <tr>
                        <th>Order</th>
                        <th>Latitude</th>
                        <th>Longitude</th>
                        <th>Altitude</th>
                        <th>Actions</th>
                      </tr>
                      </thead>

                      <tbody>
                      {waypoints.map((waypoint) => (
                          <tr key={waypoint.id}>
                            <td>{waypoint.orderNumber}</td>
                            <td>{waypoint.latitude}</td>
                            <td>{waypoint.longitude}</td>
                            <td>{waypoint.altitude} m</td>
                            <td>
                              <div className="table-actions">
                                <button
                                    type="button"
                                    onClick={() => startWaypointEdit(waypoint)}
                                >
                                  Edit
                                </button>

                                <button
                                    className="danger-text"
                                    type="button"
                                    onClick={() =>
                                        void deleteWaypoint(waypoint.id)
                                    }
                                >
                                  Delete
                                </button>
                              </div>
                            </td>
                          </tr>
                      ))}
                      </tbody>
                    </table>

                    {waypoints.length === 0 && (
                        <p className="empty-message">
                          This mission has no waypoints yet.
                        </p>
                    )}
                  </div>
                </>
            ) : (
                <div className="empty-selection">
                  <h3>No mission selected</h3>
                  <p>Select a mission from the left panel.</p>
                </div>
            )}
          </section>
        </main>
      </div>
  )
}

export default App