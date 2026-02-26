package yr.muhammadyaumil.movieapp.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import yr.muhammadyaumil.movieapp.ui.profile.event.ProfileEvent
import yr.muhammadyaumil.movieapp.ui.profile.state.ProfileState
import javax.inject.Inject

class ProfileViewModel
    @Inject
    constructor() : ViewModel() {
        private val _state = MutableStateFlow(ProfileState())
        val state: StateFlow<ProfileState> = _state

        fun onEvent(event: ProfileEvent) {
        }
    }
