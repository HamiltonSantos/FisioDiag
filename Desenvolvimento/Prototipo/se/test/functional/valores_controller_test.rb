require 'test_helper'

class ValoresControllerTest < ActionController::TestCase
  setup do
    @valore = valores(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:valores)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create valore" do
    assert_difference('Valore.count') do
      post :create, valore: { description: @valore.description, value: @valore.value, value_max: @valore.value_max, value_min: @valore.value_min }
    end

    assert_redirected_to valore_path(assigns(:valore))
  end

  test "should show valore" do
    get :show, id: @valore
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @valore
    assert_response :success
  end

  test "should update valore" do
    put :update, id: @valore, valore: { description: @valore.description, value: @valore.value, value_max: @valore.value_max, value_min: @valore.value_min }
    assert_redirected_to valore_path(assigns(:valore))
  end

  test "should destroy valore" do
    assert_difference('Valore.count', -1) do
      delete :destroy, id: @valore
    end

    assert_redirected_to valores_path
  end
end
